import React, { useState, useRef, useEffect } from 'react';

function Filter({ displayName, keywords, filteringData, onFilterChange}) {
    const [selectedKeywords, setSelectedKeywords] = useState(filteringData || []);
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const dropdownRef = useRef(null);
    const [maxLength, setMaxLength] = useState(30);

    const toggleSelection = (keyword) => {
        setSelectedKeywords((prev) => {
            const newSelectedKeywords = prev.includes(keyword)
                ? prev.filter((k) => k !== keyword)
                : [...prev, keyword];
            onFilterChange(newSelectedKeywords);
            return newSelectedKeywords;
        });
    };

    const clearSelections = () => {
        setSelectedKeywords([]);
        onFilterChange([]);
    };

    const truncateKeyword = (keyword) => {
        return keyword.length > maxLength
            ? `${keyword.substring(0, maxLength)}...`
            : keyword;
    };

    const filteredKeywords = keywords.filter((keyword) =>
        keyword.toLowerCase().includes(searchTerm.toLowerCase())
    );

    useEffect(() => {
        setSelectedKeywords(filteringData || []);
    }, [filteringData]);


    // Dynamically update maxLength based on screen size
    useEffect(() => {
        const updateMaxLength = () => {
            if (window.innerWidth < 640) {
                setMaxLength(window.innerWidth / 16);
            } else {
                setMaxLength(window.innerWidth / 70);
            }
        };

        updateMaxLength();
        window.addEventListener('resize', updateMaxLength);

        return () => {
            window.removeEventListener('resize', updateMaxLength);
        };
    }, []);

    // Close the dropdown when clicking outside
    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setIsDropdownOpen(false);
            }
        };

        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, []);

    return (
        <div className="mt-2 md:w-1/4 w-full" ref={dropdownRef}>
            <label className="block mb-1 font-medium">Vali {displayName.toLowerCase()}</label>
            <div className="relative">
                <div
                    className="flex items-center w-full border rounded p-2 bg-white cursor-pointer"
                    onClick={() => setIsDropdownOpen(!isDropdownOpen)}
                >
                    <div className="flex flex-wrap items-center gap-1 flex-grow min-w-0">
                        {selectedKeywords.length > 0 ? (
                            selectedKeywords.map((keyword, index) => (
                                <span
                                    key={index}
                                    className="bg-blue-100 text-blue-700 px-2 py-0 rounded flex items-center whitespace-nowrap overflow-hidden text-ellipsis"
                                >
                                    {truncateKeyword(keyword)}
                                    <button
                                        onClick={(e) => {
                                            e.stopPropagation();
                                            toggleSelection(keyword);
                                        }}
                                        className="relative ml-1 text-red-500"
                                    >
                                        <svg
                                            xmlns="http://www.w3.org/2000/svg"
                                            width="8"
                                            height="8"
                                            viewBox="0 0 24 24"
                                        >
                                            <path
                                                d="M23.5 20.188l-7.9-7.8 7.8-7.9-3.5-3.5-7.9 7.9-7.9-7.8-3.5 3.5 7.9 7.8-7.8 7.9 3.5 3.5 7.9-7.9 7.8 7.8z"
                                                stroke="none"
                                                fill="currentColor"
                                            />
                                        </svg>
                                    </button>
                                </span>
                            ))
                        ) : (
                            <span className="text-gray-500">Vali {displayName.toLowerCase()}</span>
                        )}
                    </div>
                    {selectedKeywords.length > 0 && (
                        <button
                            onClick={(e) => {
                                e.stopPropagation();
                                clearSelections();
                            }}
                            className="ml-2 px-2"
                            title="Clear all selections"
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24">
                                <path d="M3 6v18h18v-18h-18zm5 14c0 .552-.448 1-1 1s-1-.448-1-1v-10c0-.552.448-1 1-1s1 .448 1 1v10zm5 0c0 .552-.448 1-1 1s-1-.448-1-1v-10c0-.552.448-1 1-1s1 .448 1 1v10zm5 0c0 .552-.448 1-1 1s-1-.448-1-1v-10c0-.552.448-1 1-1s1 .448 1 1v10zm4-18v2h-20v-2h5.711c.9 0 1.631-1.099 1.631-2h5.315c0 .901.73 2 1.631 2h5.712z" />
                            </svg>
                        </button>
                    )}
                    <div className='pl-2' onClick={() => setIsDropdownOpen(!isDropdownOpen)}>
                        <div className={`transform ${isDropdownOpen ? 'rotate-180' : ''} transition-transform duration-300`}>
                            {/* Arrow icon */}
                            <svg width="16px" height="16px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M5.70711 9.71069C5.31658 10.1012 5.31658 10.7344 5.70711 11.1249L10.5993 16.0123C11.3805 16.7927 12.6463 16.7924 13.4271 16.0117L18.3174 11.1213C18.708 10.7308 18.708 10.0976 18.3174 9.70708C17.9269 9.31655 17.2937 9.31655 16.9032 9.70708L12.7176 13.8927C12.3271 14.2833 11.6939 14.2832 11.3034 13.8927L7.12132 9.71069C6.7308 9.32016 6.09763 9.32016 5.70711 9.71069Z" fill="#0F0F0F"/>
                            </svg>
                        </div>
                    </div>
                </div>
                {isDropdownOpen && (
                    <div className="absolute z-10 w-full border rounded bg-white shadow-lg max-h-[13.5rem] overflow-y-auto mt-1">
                        <input
                            type="text"
                            placeholder={`Otsi ${displayName.toLowerCase()}...`}
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                            className="w-full p-2 border-b text-sm"
                        />
                        {filteredKeywords.map((keyword) => (
                            <label
                                key={keyword}
                                className="flex items-center p-2 hover:bg-gray-100 cursor-pointer w-full text-sm"
                            >
                                <input
                                    type="checkbox"
                                    value={keyword}
                                    checked={selectedKeywords.includes(keyword)}
                                    onChange={() => toggleSelection(keyword)}
                                    className="mr-2 cursor-pointer"
                                />
                                {keyword}
                            </label>
                        ))}
                        {filteredKeywords.length === 0 && (
                            <div className="p-2 text-gray-500 text-sm">Märksõna ei leitud</div>
                        )}
                    </div>
                )}
            </div>
        </div>
    );
}

export default Filter;
