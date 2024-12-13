import React, {useState, useEffect, useCallback} from 'react';
import SearchResult from './SearchResult';
import SearchHeader from './SearchHeader';
import Filters from './Filters';
import SearchFooter from "./SearchFooter";

function Hero({selectedCategory, categoryChange}) {
    const [items, setItems] = useState({products: []});
    const [sortedProducts, setSortedProducts] = useState([]);
    const [q, setQ] = useState('');
    const [inputValue, setInputValue] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [selectedLocations, setSelectedLocations] = useState([]);
    const [filteringData, setFilteringData] = useState([])
    const productsPerPage = 25; // currently a constant
    const [isLoading, setIsLoading] = useState(false);
    const [searchId, setSearchId] = useState(null);

    const queryBuilder = (val) => {
        if (selectedCategory) return val.concat('&subcategory=', selectedCategory.subcategory)
        else return val
    }

    useEffect(() => {
        let abortController = new AbortController(); // Create an instance of AbortController
        const signal = abortController.signal;

        if (q) {
            //console.log(q);
            setIsLoading(true);

            // Fetch data with abort signal
            fetch(`http://13.61.25.83:8080/request/search?keyword=${q}`, { signal })
            //fetch(`http://localhost:8080/request/search?keyword=${q}`, {signal})
                .then(async (response) => {
                    const decoder = new TextDecoder('utf-8');
                    const decodedResponse = decoder.decode(await response.arrayBuffer());
                    return JSON.parse(decodedResponse);
                })
                .then((res) => {
                    setSearchId(res.searchId);
                    setItems(res || {products: []});

                    const sorted = res.products?.sort((a, b) => parseFloat(a.price) - parseFloat(b.price)) || [];
                    //console.log(sorted)
                    setSortedProducts(sorted);
                    setCurrentPage(1);
                })
                .then(() => setIsLoading(false))
                .catch((err) => {
                    if (err.name === "AbortError") {
                        console.log("Fetch aborted");
                    } else {
                        console.error("Fetch error:", err);
                    }
                });
        }

        return () => {
            // Abort fetch on cleanup or when `q` changes
            abortController.abort();
        };
    }, [q]);
    useEffect(() => {
        if (selectedLocations.length === 0) {
            // If no locations are selected, display all products
            setSortedProducts(items.products);
        } else {
            // Filter products that have at least one matching location
            const filteredProductsByLocation = items.products.filter((product) =>
                selectedLocations.some((requiredLocation) =>
                    product.stock.locations.some(
                        (loc) => loc.location.locationName === requiredLocation
                    )
                )
            );

            setSortedProducts(filteredProductsByLocation);
        }
    }, [selectedLocations, items.products]);


    useEffect(() => {
        if (selectedCategory) {
            setQ(`&subcategory=${selectedCategory.subcategory}`)
            setInputValue('')
            setFilteringData([]);
            setSearchId(null);
        } // Update query upon category selection
    }, [selectedCategory])

    const generateFilterUrl = useCallback((filteringData) => {
        const filters = filteringData.map(sublist => sublist.join(';'));
        const filtersParam = encodeURIComponent(filters.join('_'));
        //const url = `http://localhost:8080/request/filter?searchId=${encodeURIComponent(searchId)}&filters=${filtersParam}`;
        const url = `http://13.61.25.83:8080/request/filter?searchId=${encodeURIComponent(searchId)}&filters=${filtersParam}`;
        //console.log('Generated URL:', url);
        return url;
    }, [searchId]);


    useEffect(() => {
        if (searchId != null) {
            // Generate the URL based on filteringData
            const url = generateFilterUrl(filteringData);

            // Log the URL to check it
            //console.log("Fetching data with URL:", url);

            setIsLoading(true); // Set loading to true while fetching data

            // Fetch the filtered data
            fetch(url)
                .then(async (response) => {
                    const decoder = new TextDecoder('utf-8');
                    const decodedResponse = decoder.decode(await response.arrayBuffer());
                    return JSON.parse(decodedResponse);
                })
                .then((res) => {
                    // Assuming the response format is the same as in your previous fetch call
                    console.log(res);
                    setItems(res || {products: []});
                    const sorted = res.products?.sort((a, b) => parseFloat(a.price) - parseFloat(b.price)) || [];
                    setSortedProducts(sorted);
                    setCurrentPage(1);

                })
                .then(() => setIsLoading(false)) // Set loading to false after fetching
                .catch((err) => console.error("Fetch error:", err)); // Handle errors
        }
    }, [filteringData, generateFilterUrl, searchId]); // This effect will trigger when filteringData changes


    const indexOfLastProduct = currentPage * productsPerPage;
    const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
    const currentProducts = sortedProducts.slice(indexOfFirstProduct, indexOfLastProduct);
    useEffect(() => {
        const totalPages = Math.ceil(sortedProducts.length / productsPerPage);
        if (currentPage > totalPages) {
            setCurrentPage(1); // Reset to first page
        }
    }, [sortedProducts, currentPage, productsPerPage]);
    return (
        <div className='w-full md:w-3/4 h-max md:p-10 p-4'>
            <p className='mb-2 text-sm'>{`${selectedCategory ? 'Valitud kategooria: '.concat(selectedCategory.category).concat(' / ').concat(selectedCategory.subcategory) : ''} `}<span
                className='underline text-red-600 hover:cursor-pointer' onClick={() => {
                categoryChange(null)
            }}>{`${selectedCategory ? 'Eemalda' : ''}`}</span></p>
            <form onSubmit={(e) => {
                e.preventDefault();
                setQ(queryBuilder(inputValue));
            }} className='flex drop-shadow-md rounded mb-4 bg-white'>
                <input
                    className='w-full h-14 pl-2 rounded'
                    type='text'
                    placeholder={`Otsi toodet ${selectedCategory ? 'kategooriast '.concat(selectedCategory.subcategory) : ''}`}
                    onChange={(e) => setInputValue(e.target.value)}
                    value={inputValue}
                />
                <button type='submit' className='mx-2'>
                    <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path
                            d="M15.7955 15.8111L21 21M18 10.5C18 14.6421 14.6421 18 10.5 18C6.35786 18 3 14.6421 3 10.5C3 6.35786 6.35786 3 10.5 3C14.6421 3 18 6.35786 18 10.5Z"
                            stroke="#000000" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                    </svg>
                </button>
            </form>

            <Filters selectedLocations={selectedLocations}
                     setSelectedLocations={setSelectedLocations}
                     filteringData={filteringData}
                     setFilteringData={setFilteringData}
                     filters={selectedCategory != null ? selectedCategory.filters : []}
                     clearFiltersTrigger={selectedCategory}
                     searchId={searchId}
            />

            <SearchHeader
                category={selectedCategory}
                isLoading={isLoading}
                totalProducts={sortedProducts.length}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                productsPerPage={productsPerPage}
            />

            <div className='w-full h-max'>
                {isLoading ? (
                    <div className='w-full mt-4 flex justify-center'>
                        <p className='animate-pulse text-sm'>Otsime tooteid, tänan kannatlikuse eest.</p>
                    </div>
                ) : selectedLocations.length > 0 && sortedProducts.length === 0 ? (
                    <div className='w-full mt-4 flex justify-center'>
                        <p className='text-sm'>Tooteid selles asukohas ei leidu.</p>
                    </div>
                ) : (
                    <ul className='flex flex-col gap-4'>
                        {currentProducts?.map((item, index) => {
                            const locations = item.stock?.locations || [];
                            return (
                                sortedProducts.length > 0 && (
                                    <li key={index}>
                                        <SearchResult
                                            name={item.name}
                                            store={item.store}
                                            linkToPicture={item.linkToPicture}
                                            linkToProduct={item.linkToProduct}
                                            price={item.price.toFixed(2)}
                                            unit={item.unit}
                                            locations={locations}
                                            selectedLocations={selectedLocations} // Pass selected locations to filter
                                        />
                                    </li>
                                )
                            );
                        })}
                    </ul>
                )}
            </div>

            <SearchFooter
                isLoading={isLoading}
                totalProducts={sortedProducts.length}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                productsPerPage={productsPerPage}
            />

        </div>
    );
}

export default Hero;