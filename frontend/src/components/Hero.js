import React, { useState, useEffect } from 'react';
import SearchResult from './SearchResult';
import SearchHeader from './SearchHeader';
import Filters from './Filters';

function Hero({ selectedCategory }) {
    const [items, setItems] = useState({ products: [] });
    const [sortedProducts, setSortedProducts] = useState([]);
    const [q, setQ] = useState('');
    const [inputValue, setInputValue] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [selectedLocations, setSelectedLocations] = useState([]);
    const productsPerPage = 25; // currently a constant
    const [isLoading, setIsLoading] = useState(false);

    const queryBuilder = (val) => {
        if (selectedCategory) return val.concat('&subcategory=', selectedCategory.subcategory)
        else return val
    }

    useEffect(() => {
        if (q) {
            setIsLoading(true);
            fetch(`http://16.16.186.149:8080/request/search?keyword=${q}`)
            //fetch(`http://localhost:8080/request/search?keyword=${q}`)
                .then(async (response) => {
                    const decoder = new TextDecoder('utf-8');
                    const decodedResponse = decoder.decode(await response.arrayBuffer());
                    return JSON.parse(decodedResponse);
                })
                .then((res) => {
                    setItems(res);
                    const sorted = res.products?.sort((a, b) => parseFloat(a.price) - parseFloat(b.price)) || [];
                    setSortedProducts(sorted);
                    setCurrentPage(1);
                })
                .then(() => setIsLoading(false))
                .catch((err) => console.error("Fetch error:", err));
        }
    }, [q]);

    useEffect(() => {
        if (selectedCategory) { 
            let query = selectedCategory.subcategory.concat('&subcategory=', selectedCategory.subcategory)
            setQ(query)
        } // Update query upon category selection
    }, [selectedCategory])

    const indexOfLastProduct = currentPage * productsPerPage;
    const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
    const currentProducts = sortedProducts.slice(indexOfFirstProduct, indexOfLastProduct);

    return (
        <div className='w-full md:w-3/4 h-max md:p-10 p-4'>
            <form onSubmit={(e) => {
                e.preventDefault();
                setQ(queryBuilder(inputValue));
            }} className='flex drop-shadow-md rounded mb-4 bg-white'>
                <input
                    className='w-full h-14 pl-2 rounded'
                    type='text'
                    placeholder={`Otsi toodet ${selectedCategory ? 'kategooriast '.concat(selectedCategory.subcategory) : ''}`}
                    onChange={(e) => setInputValue(e.target.value)}
                />
                <button type='submit' className='mx-2'>
                    <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path
                            d="M15.7955 15.8111L21 21M18 10.5C18 14.6421 14.6421 18 10.5 18C6.35786 18 3 14.6421 3 10.5C3 6.35786 6.35786 3 10.5 3C14.6421 3 18 6.35786 18 10.5Z"
                            stroke="#000000" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" />
                    </svg>
                </button>
            </form>

            <Filters selectedLocations={selectedLocations} setSelectedLocations={setSelectedLocations} />

            <SearchHeader
                category={selectedCategory}
                isLoading={isLoading}
                totalProducts={items.products.length}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                productsPerPage={productsPerPage}
            />

            <div className='w-full h-max'>
                {isLoading ?
                    <div className='w-full mt-4 flex justify-center'>
                        <svg className='animate-spin' width="40px" height="40px" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M4.5 12.5C4.5 16.9183 8.08172 20.5 12.5 20.5C16.9183 20.5 20.5 16.9183 20.5 12.5C20.5 8.08172 16.9183 4.5 12.5 4.5" stroke="#121923" strokeWidth="1.2" />
                        </svg>
                    </div>
                    :
                    (<ul className='flex flex-col gap-4'>
                        {currentProducts?.map((item, index) => {
                            const locations = item.stock?.locations || [];
                            return (
                                sortedProducts.length > 0
                                    ? (
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
                                    : <></>
                            );
                        })}
                    </ul>)
                }
            </div>
        </div>
    );
}

export default Hero;
