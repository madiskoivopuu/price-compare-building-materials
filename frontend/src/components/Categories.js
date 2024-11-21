import React, { useState, useEffect } from 'react';
import Category from "./Category";

function Categories({isMobileMenuExtended, categoryChange}) {
    
    const [categories, setCategories] = useState([])
    const [selectedSubcategory, setSelectedSubcategory] = useState(null)
    const [selectedCat, setSelectedCat] = useState(null)

    

    const subcategoryChange = (subCat) =>  { 
        setSelectedSubcategory(subCat)
        if(subCat) {
            categoryChange({ category: 'cat', subcategory: subCat });
        }
    }

    useEffect(() => {
        fetch('http://16.16.186.149:8080/request/categories')  // This is for testing environment
        //fetch('http://localhost:8080/request/categories')  // This is for testing environment
            .then(response => response.json())
            .then(data => {
                setCategories(data.categories);
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
                    <li key={index} onClick={() => {setSelectedCat(index)}}>
                        <Category category={category} subcategoryChange={subcategoryChange} active={index === selectedCat}/>
                    </li>
                ))}
            </ul>
        </div>
        </>
    );
}

export default Categories;
