import React, { useState, useEffect, useRef } from 'react';
import LocationNameEnum from '../utils/LocationNameEnum';
function LocationFilter({ selectedLocations, setSelectedLocations }) {
    // Location options


    // State to handle the dropdown open/close and search term
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const dropdownRef = useRef(null);

    // Function to toggle selection of a location
    const toggleSelection = (location) => {
        if (selectedLocations.includes(location)) {
            setSelectedLocations(selectedLocations.filter(l => l !== location));
        } else {
            setSelectedLocations([...selectedLocations, location]);
        }
    };

    // Function to clear all selections and reset the search term
    const clearSelections = () => {
        setSelectedLocations([]);
        setSearchTerm('');
    };

    // Filter locations based on the search term
    const filteredLocations = Object.entries(LocationNameEnum).filter(([key, value]) =>
        value.toLowerCase().includes(searchTerm.toLowerCase())
    );

    // Close the dropdown when clicking outside
    useEffect(() => {
        const handleOutsideClick = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setIsDropdownOpen(false);
            }
        };

        document.addEventListener('mousedown', handleOutsideClick);
        return () => {
            document.removeEventListener('mousedown', handleOutsideClick);
        };
    }, []);

    return (
        <div className='mt-2 md:w-1/3 w-full' ref={dropdownRef}>
            {/* Label for the filter */}
            <label className='block mb-1 font-medium'>Vali asukoht</label>

            {/* Container for the dropdown */}
            <div className='relative'>
                {/* Dropdown display and toggle */}
                <div className='flex items-center w-full border rounded p-2 bg-white cursor-pointer' onClick={() => setIsDropdownOpen(!isDropdownOpen)}>
                    {/* Display selected locations */}
                    <div className='flex flex-wrap items-center gap-1 flex-grow min-w-0'>
                        {selectedLocations.length > 0 ? (
                            selectedLocations.map((location, index) => (
                                <span key={index} className='bg-blue-100 text-blue-700 px-2 py-0 rounded flex items-center whitespace-nowrap'>
                                    {LocationNameEnum[location]}
                                    {/* Button to remove individual selected location */}
                                    <button
                                        onClick={(e) => {
                                            e.stopPropagation(); // Prevent dropdown toggle
                                            toggleSelection(location);
                                        }}
                                        className='ml-1 text-red-500'
                                    >
                                        {/* Close icon */}
                                        <svg xmlns="http://www.w3.org/2000/svg" width="8" height="8" viewBox="0 0 24 24">
                                            <path d="M23.5 20.188l-7.9-7.8 7.8-7.9-3.5-3.5-7.9 7.9-7.9-7.8-3.5 3.5 7.9 7.8-7.8 7.9 3.5 3.5 7.9-7.9 7.8 7.8z" stroke="none" fill="currentColor"/>
                                        </svg>
                                    </button>
                                </span>
                            ))
                        ) : (
                            // Placeholder text when no location is selected
                            <span className='text-gray-500'>Vali asukoht</span>
                        )}
                    </div>

                    {/* Button to clear all selections */}
                    {selectedLocations.length > 0 && (
                        <button
                            onClick={(e) => {
                                e.stopPropagation(); // Prevent dropdown toggle
                                clearSelections();
                            }}
                            className='ml-2 px-2'
                            title='Clear all selections'
                        >
                            {/* Clear icon */}
                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24">
                                <path d="M3 6v18h18v-18h-18zm5 14c0 .552-.448 1-1 1s-1-.448-1-1v-10c0-.552.448-1 1-1s1 .448 1 1v10zm5 0c0 .552-.448 1-1 1s-1-.448-1-1v-10c0-.552.448-1 1-1s1 .448 1 1v10zm5 0c0 .552-.448 1-1 1s-1-.448-1-1v-10c0-.552.448-1 1-1s1 .448 1 1v10zm4-18v2h-20v-2h5.711c.9 0 1.631-1.099 1.631-2h5.315c0 .901.73 2 1.631 2h5.712z"/>
                            </svg>
                        </button>
                    )}
                    {/* Clickable area around the arrow */}
                    <div className='pl-2' onClick={() => setIsDropdownOpen(!isDropdownOpen)}>
                        <div className={`transform ${isDropdownOpen ? 'rotate-180' : ''} transition-transform duration-300`}>
                            {/* Arrow icon */}
                            <svg width="16px" height="16px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M5.70711 9.71069C5.31658 10.1012 5.31658 10.7344 5.70711 11.1249L10.5993 16.0123C11.3805 16.7927 12.6463 16.7924 13.4271 16.0117L18.3174 11.1213C18.708 10.7308 18.708 10.0976 18.3174 9.70708C17.9269 9.31655 17.2937 9.31655 16.9032 9.70708L12.7176 13.8927C12.3271 14.2833 11.6939 14.2832 11.3034 13.8927L7.12132 9.71069C6.7308 9.32016 6.09763 9.32016 5.70711 9.71069Z" fill="#0F0F0F"/>
                            </svg>
                        </div>
                    </div>
                </div>

                {/* Dropdown content */}
                {isDropdownOpen && (
                    <div className='absolute z-10 w-full border rounded bg-white shadow-lg max-h-[13.5rem] overflow-y-auto mt-1'>
                        {/* Search input */}
                        <input
                            type='text'
                            placeholder='Otsi asukohta...'
                            value={searchTerm}
                            onChange={(e) => {
                                e.stopPropagation(); // Prevent dropdown toggle when typing
                                setSearchTerm(e.target.value);
                            }}
                            className='w-full p-2 border-b text-sm'
                        />
                        {/* List of filtered locations */}
                        {filteredLocations.map(([key, value]) => (
                            <label key={key} className='flex items-center p-2 hover:bg-gray-100 cursor-pointer w-full text-sm'>
                                <input
                                    type='checkbox'
                                    value={key}
                                    checked={selectedLocations.includes(key)}
                                    onChange={() => toggleSelection(key)}
                                    className='mr-2 cursor-pointer'
                                />
                                {value}
                            </label>
                        ))}
                        {/* Message when no locations are found */}
                        {filteredLocations.length === 0 && (
                            <div className='p-2 text-gray-500 text-sm'>Asukohti ei leitud</div>
                        )}
                    </div>
                )}
            </div>
        </div>
    );
}

export default LocationFilter;
