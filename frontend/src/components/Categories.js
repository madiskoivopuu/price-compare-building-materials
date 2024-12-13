import React, { useState, useEffect } from 'react';
import Category from "./Category";

function Categories({isMobileMenuExtended, categoryChange, selectedCategory, unextendMobileMenu}) {
    
    const [categories, setCategories] = useState([])
    const [selectedSubcategory, setSelectedSubcategory] = useState(null)
    const [selectedCat, setSelectedCat] = useState(null)
    const [selectedCatName, setSelectedCatName] = useState('')

    const donothing = (item) => {};

    const subcategoryChange = (subCat) =>  { 
        donothing(selectedSubcategory);
        
        setSelectedSubcategory(subCat)
        if(subCat) {
            categoryChange({ category: selectedCatName, subcategory: subCat.displayName,  filters: subCat.filters})
            unextendMobileMenu()
        }
    }

    useEffect(() => {
        fetch('http://13.61.25.83:8080/request/categories')  // This is for testing environment
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
                    <li key={index} onClick={() => {setSelectedCat(index); setSelectedCatName(category.name)}}>
                        <Category category={category} subcategoryChange={subcategoryChange} active={selectedCategory ? index === selectedCat : false}/>
                    </li>
                ))}
            </ul>
        </div>
        </>
    );
}

export default Categories;
