import React, { useState } from 'react';

function Category({ category }) {
    const [isExpanded, setIsExpanded] = useState(false);

    // Toggle the subcategory list when clicked
    const toggleExpand = () => {
        setIsExpanded(!isExpanded);
    };

    return (
        <div>
            {/* Main category button */}
            <button
                onClick={toggleExpand}
                className="flex justify-between items-center w-full p-2 bg-gray-100 hover:bg-gray-200"
            >
                <span>{category.name}</span>
                {/* Arrow icon changes based on the expanded state */}
                <span className={`transform transition-transform ${isExpanded ? 'rotate-180' : ''}`}>
                    ▼
                </span>
            </button>

            {/* Subcategories dropdown, visible only when expanded */}
            {isExpanded && (
                <ul className="pl-4 mt-2 space-y-1">
                    {category.subcategories.map((subcategory, index) => (
                        <li key={index} className="p-1 hover:bg-gray-100">
                            {subcategory}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default Category;
