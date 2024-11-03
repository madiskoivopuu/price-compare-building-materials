import React, { useState, useEffect } from 'react';
import Category from "./Category";

function Categories() {
    const [categories, setCategories] = useState([])
    const [isExpanded, setIsExpanded] = useState(false)

    const toggleExpand = () => {
        setIsExpanded(!isExpanded);
    };

    const resizeSetExpandFalse = () => {
        setIsExpanded(false)
    }

    useEffect(() => {
        window.addEventListener('resize', resizeSetExpandFalse)
    }, [])

    useEffect(() => {
        //fetch('http://16.16.186.149:8080/request/categories')  // This is for testing environment
        fetch('http://localhost:8080/request/categories')  // This is for testing environment
            .then(response => response.json())
            .then(data => {
                setCategories(data.categories);
                console.log(data)
            })
            .catch(error => {
                console.error('Error fetching categories:', error);

            });
    }, []);

    return (
        <>
        <button 
            className={`fixed top-3 left-3 w-10 h-10 md:hidden z-[100] transition ease-in ${isExpanded ? 'rotate-45' : ''}`}
            onClick={toggleExpand}
        >
            <svg width="40px" height="40px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fillRule="evenodd" clipRule="evenodd" d="M12 4C12.5523 4 13 4.44772 13 5V11H19C19.5523 11 20 11.4477 20 12C20 12.5523 19.5523 13 19 13H13V19C13 19.5523 12.5523 20 12 20C11.4477 20 11 19.5523 11 19V13H5C4.44772 13 4 12.5523 4 12C4 11.4477 4.44772 11 5 11H11V5C11 4.44772 11.4477 4 12 4Z" fill="#000000"/>
            </svg>
        </button>
        <div className={`shadow-inner bg-white w-1/4 py-8 pl-8 fixed -left-80 md:relative md:left-0 z-[99]  ${isExpanded ? 'left-0 w-3/4 bg-white top-16 ease-in transition duration-100 opacity-95' : ''}`}>
            <h1 className="font-bold">Kategooriad</h1>
            <ul className="p-2">
                {categories.map((category, index) => (
                    <li key={index}>
                        {}
                        <Category category={category} />
                    </li>
                ))}
            </ul>
        </div>
        </>
    );
}

export default Categories;
