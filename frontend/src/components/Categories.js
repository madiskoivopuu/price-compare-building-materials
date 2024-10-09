import React, { useState, useEffect } from 'react';
import Category from "./Category";

function Categories() {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/categories')  // This is for testing environment
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
        <div className="w-1/4 h-max p-8 hidden md:block max-h-screen overflow-y-auto">
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
    );
}

export default Categories;
