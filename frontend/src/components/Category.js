import React, { useEffect, useState } from 'react';

function Category({ category, subcategoryChange, active }) {
    const [selectedSubcat, setSelectedSubcat] = useState(null)
    const [isExpanded, setIsExpanded] = useState(false)

    const toggleExpand = () => {
        setIsExpanded(!isExpanded)
    }
    
    useEffect(() => {if (!active) {
        setIsExpanded(false)
        setSelectedSubcat(null)
    }}, [active])

    return (
        <div>
            {/* Main category button */}
            <button
                onClick={toggleExpand}
                className="flex items-center w-full hover:underline truncate"
            >
                <span>{category.name}</span>
                {/* Arrow icon changes based on the expanded state */}
                <span className={`ml-1 transform transition-transform ${isExpanded ? '' : '-rotate-90'}`}>
                <svg width="14px" height="14px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M5.70711 9.71069C5.31658 10.1012 5.31658 10.7344 5.70711 11.1249L10.5993 16.0123C11.3805 16.7927 12.6463 16.7924 13.4271 16.0117L18.3174 11.1213C18.708 10.7308 18.708 10.0976 18.3174 9.70708C17.9269 9.31655 17.2937 9.31655 16.9032 9.70708L12.7176 13.8927C12.3271 14.2833 11.6939 14.2832 11.3034 13.8927L7.12132 9.71069C6.7308 9.32016 6.09763 9.32016 5.70711 9.71069Z" fill="#0F0F0F"/>
                </svg>
                </span>
            </button>

            {/* Subcategories dropdown, visible only when expanded */}
            {isExpanded && (
                <ul className="pl-4 mt-2 space-y-1">
                    {category.subcategories.map((subcategory, index) => (
                        <li key={index} className="hover:underline hover:cursor-pointer truncate" onClick={() => {setSelectedSubcat(index); subcategoryChange(subcategory)}}>
                            {
                            selectedSubcat === index
                            ?
                            <strong>{subcategory.displayName}</strong>
                            :
                            subcategory.displayName 
                            }
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default Category;
