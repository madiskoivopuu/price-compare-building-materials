import React, { useState, useEffect } from 'react';
import Category from "./Category";

function Categories({isMobileMenuExtended}) {
    const [categories, setCategories] = useState([])

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
        <div className={`shadow-inner bg-white w-1/4 py-8 pl-8 absolute md:relative -left-80 md:left-0 z-[99]  ${isMobileMenuExtended ? 'left-0 w-3/4 bg-white  top-16 opacity-95' : ''}`}>
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
