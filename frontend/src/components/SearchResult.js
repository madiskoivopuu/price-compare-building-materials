import React, { useState, useRef, useEffect } from 'react';
import LocationNameEnum from '../utils/LocationNameEnum';
function SearchResult({ name, linkToPicture, linkToProduct, price, unit, store, locations, selectedLocations }) {
    const logos = {
        "BAUHOF": "bauhof.png",
        "KRAUTA": "krauta.jpg",
        "ESPAK": "espak.jpg",
        "DECORA": "decora.png"
    };


    const [isExpanded, setIsExpanded] = useState(false);
    const [showLocations, setShowLocations] = useState(false);
    const dropdownRef = useRef(null);

    const handleOutsideClick = (event) => {
        if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
            setShowLocations(false);
            setIsExpanded(false);
        }
    };

    useEffect(() => {
        document.addEventListener('mousedown', handleOutsideClick);
        return () => {
            document.removeEventListener('mousedown', handleOutsideClick);
        };
    }, []);

    // Show all locations if no filter is applied; otherwise, filter based on the selected locations
    const filteredLocations = selectedLocations.length > 0
        ? locations.filter(location => selectedLocations.includes(location.location?.locationName))
        : locations;

    // Determine if the button should be greyed out
    const isUnavailable = filteredLocations.length === 0;

    return (
        <div className='w-full h-20 rounded flex items-center justify-between pr-4 bg-white shadow-md relative'>
            {/* CARD */}
            <div className='flex justify-start items-center h-full'>
                {/* IMAGE */}
                <div className='w-20 h-20 p-1 flex-none'>
                    <img alt={name} src={linkToPicture} className='object-contain w-full h-full' />
                </div>
            </div>
            {/* TEXT */}
            <div className='flex-grow overflow-hidden'>
                <h1 className='truncate text-sm'>{name}</h1>
            </div>
            {/* BUTTONS AND PRICE */}
            <div className='w-max h-full flex items-center gap-4 ml-2 flex-shrink-0 relative'>
                <h1><strong>{price}€</strong><small>&nbsp;/&nbsp;{unit}</small></h1>

                {/* AVAILABILITY WITH DROPDOWN */}
                <div className="relative" ref={dropdownRef}>
                    <button
                        onClick={() => setShowLocations(!showLocations)}
                        className={`w-64 h-max border rounded p-1 relative ${isUnavailable ? 'bg-gray-300 text-gray-600 cursor-not-allowed' : 'border-gray-700'}`}
                        disabled={isUnavailable}
                    >
                        {isUnavailable ? 'Asukoht pole saadaval' : 'Saadavus'}
                        <div className={`absolute right-2 top-1/2 transform -translate-y-1/2 transition-transform duration-300 ${showLocations ? 'rotate-180' : ''}`}>
                            <svg width="14px" height="14px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M5.70711 9.71069C5.31658 10.1012 5.31658 10.7344 5.70711 11.1249L10.5993 16.0123C11.3805 16.7927 12.6463 16.7924 13.4271 16.0117L18.3174 11.1213C18.708 10.7308 18.708 10.0976 18.3174 9.70708C17.9269 9.31655 17.2937 9.31655 16.9032 9.70708L12.7176 13.8927C12.3271 14.2833 11.6939 14.2832 11.3034 13.8927L7.12132 9.71069C6.7308 9.32016 6.09763 9.32016 5.70711 9.71069Z" fill="#0F0F0F"/>
                            </svg>
                        </div>
                    </button>

                    {/* LOCATIONS DROPDOWN */}
                    {showLocations && filteredLocations.length > 0 && (
                        <div className='absolute top-full left-0 w-64 bg-white border rounded shadow-lg z-50 max-h-40 overflow-y-auto'>
                            <ul className='text-sm'>
                                {filteredLocations.map((location, index) => (
                                    <li key={index} className='p-2'>
                                        <div className='flex justify-between items-center'>
                                            <span>
                                                {location.location && LocationNameEnum[location.location.locationName]
                                                    ? LocationNameEnum[location.location.locationName]
                                                    : location.location?.locationName || 'Asukoht pole saadaval'},&nbsp;
                                                {location.location?.address ? (
                                                    <a
                                                        href={`https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(location.location.address)}`}
                                                        target="_blank"
                                                        rel="noopener noreferrer"
                                                        className="text-blue-600 underline"
                                                    >
                                                        {location.location.address}
                                                    </a>
                                                ) : (
                                                    'Teadmata'
                                                )}
                                            </span>
                                            <span>{location.quantityText ? `${location.quantityText}` : 'Teadmata'}</span>
                                        </div>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    )}
                </div>

                {/* "E-poodi" Button */}
                <a href={linkToProduct} target="_blank" rel="noopener noreferrer" className={`md:block ${isExpanded ? '' : 'hidden'}`}>
                    <button className='w-24 h-max border border-gray-700 rounded p-1'>E-poodi</button>
                </a>

                {/* Store Logo */}
                <div className={`transition duration-150 ease-in w-24 h-max md:block ${isExpanded ? '' : 'hidden'}`}>
                    <img src={`/${logos[store]}`} alt={store} className='object-contain' />
                </div>
            </div>
        </div>
    );
}

export default SearchResult;
