import React from 'react'
import {useState, useEffect} from 'react'
import SearchResult from './SearchResult'
import SearchHeader from './SearchHeader';

function Hero() {
    const [items, setItems] = useState({products: []})
    const [q, setQ] = useState('')
    const [inputValue, setInputValue] = useState('')
    const [currentPage, setCurrentPage] = useState(1);
    const productsPerPage = 25; //currently a constant

    useEffect(() => {
        if (q) {
            fetch(`http://localhost:8080/request/search?keyword=${q}`)
                .then((res) => res.json())
                .then((res) => {
                    const sortedProducts = res.products?.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
                    setItems({...res, products: sortedProducts});
                    setCurrentPage(1);
                })
                .catch((err) => console.error("Fetch error:", err));
        }
    }, [q]);

    const indexOfLastProduct = currentPage * productsPerPage;
    const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
    const currentProducts = items.products?.slice(indexOfFirstProduct, indexOfLastProduct);
    console.log(currentProducts)


    return (
        <div className='w-full md:w-3/4 h-max p-10'>
            <form onSubmit={(e) => {
                e.preventDefault()
                setQ(inputValue)
            }}
                  className='flex border-2 border-gray-200 rounded mb-4'
            >
                <input
                    className='w-full h-12 pl-2'
                    type='text'
                    placeholder='Otsi toodet'
                    onChange={(e) => setInputValue(e.target.value)}
                >
                </input>
                <button type='submit' className='mx-2'>
                    <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path
                            d="M15.7955 15.8111L21 21M18 10.5C18 14.6421 14.6421 18 10.5 18C6.35786 18 3 14.6421 3 10.5C3 6.35786 6.35786 3 10.5 3C14.6421 3 18 6.35786 18 10.5Z"
                            stroke="#000000" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                    </svg>
                </button>
            </form>

            <SearchHeader
                totalProducts={items.products.length}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                productsPerPage={productsPerPage}
            />

            <div className='w-full h-max'>
                <ul className='flex flex-col gap-4'>
                    {currentProducts?.map((item, index) =>
                        items.products.length > 0
                            ?
                            (<li key={index}>
                                <SearchResult
                                    name={item.name}
                                    linkToPicture={item.linkToPicture}
                                    linkToProduct={item.linkToProduct}
                                    price={item.price}
                                />
                            </li>)
                            :
                            <li></li>
                    )}
                </ul>
            </div>
        </div>
    )
}

export default Hero