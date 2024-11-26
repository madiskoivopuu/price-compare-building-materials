import React, { useState, useRef, useEffect } from 'react';
import LocationNameEnum from '../utils/LocationNameEnum';
function SearchResult({ name, linkToPicture, linkToProduct, price, unit, store, locations, selectedLocations }) {
    const logos = {
        "BAUHOF": "bauhof.png",
        "KRAUTA": "krauta.jpg",
        "ESPAK": "espak.jpg",
        "DECORA": "decora.png",
        "PUUMARKET": "puumarket.png",
        "EHITUSEABC": 'ehituseAbc.png',
        "EHOMER": "ehomer.png",
    };


    const [showLocations, setShowLocations] = useState(false)
    const dropdownRef = useRef(null)

    const [isShowingInfo, setIsShowingInfo] = useState(false) // Mobile view product info (store, location, etc.)

    const handleOutsideClick = (event) => {
        if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
            setShowLocations(false);
        }
    };

    useEffect(() => {
        document.addEventListener('mousedown', handleOutsideClick);
        return () => {
            document.removeEventListener('mousedown', handleOutsideClick);
        };
    }, []);

    const resizeSetExpandFalse = () => {
        setIsShowingInfo(false)
    }
    
    useEffect(() => {
        window.addEventListener('resize', resizeSetExpandFalse)
    }, [])

    // Show all locations if no filter is applied; otherwise, filter based on the selected locations
    const filteredLocations = selectedLocations.length > 0
        ? locations.filter(location => selectedLocations.includes(location.location?.locationName))
        : locations;

    // Determine if the button should be greyed out
    const isUnavailable = filteredLocations.length === 0;

    return (
        <div className={`w-full h-16 sm:h-20 rounded flex items-center justify-between pr-4 bg-white shadow-md relative`}>
            {/* CARD */}
            <div className='flex justify-start items-center h-full'>
                {/* IMAGE */}
                <div className={`w-16 h-16 sm:w-20 sm:h-20 p-1 flex-none ${isShowingInfo ? 'hidden' : ''}`}>
                    <img alt={name}
                         src={linkToPicture}
                         className='object-contain w-full h-full'
                         onError={(e) => {
                             e.target.onerror = null
                             e.target.src = 'product-placeholder.png'
                         }}
                    />
                </div>
            </div>
            {/* TEXT */}
            <div className={`flex-grow overflow-hidden ${isShowingInfo ? 'hidden' : ''}`}>
                <h1 className='truncate text-sm'>{name}</h1>
            </div>
            {/* BUTTONS AND PRICE */}
            <div className='w-max h-full flex items-center gap-4 ml-2 flex-shrink-0 relative'>
                <h1 className={`${isShowingInfo ? 'hidden' : ''}`}><strong>{price}€</strong><small>&nbsp;/&nbsp;{unit}</small></h1>

                {/* AVAILABILITY WITH DROPDOWN */}
                <div className="relative" ref={dropdownRef}>
                    <button
                        onClick={() => setShowLocations(!showLocations)}
                        className={`text-sm sm:text-base w-36 sm:w-44 h-max border md:block rounded p-1 relative ${isUnavailable ? 'bg-gray-300 text-gray-600 cursor-not-allowed' : 'border-gray-700'} ${isShowingInfo ? '' : 'hidden'}`}
                        disabled={isUnavailable}
                    >
                        {isUnavailable ? 'Info puudub' : 'Saadavus'}
                        <div className={`absolute right-2 top-1/2 transform -translate-y-1/2 transition-transform duration-300 ${showLocations ? 'rotate-180' : ''}`}>
                            <svg width="14px" height="14px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M5.70711 9.71069C5.31658 10.1012 5.31658 10.7344 5.70711 11.1249L10.5993 16.0123C11.3805 16.7927 12.6463 16.7924 13.4271 16.0117L18.3174 11.1213C18.708 10.7308 18.708 10.0976 18.3174 9.70708C17.9269 9.31655 17.2937 9.31655 16.9032 9.70708L12.7176 13.8927C12.3271 14.2833 11.6939 14.2832 11.3034 13.8927L7.12132 9.71069C6.7308 9.32016 6.09763 9.32016 5.70711 9.71069Z" fill="#0F0F0F"/>
                            </svg>
                        </div>
                    </button>

                    {/* LOCATIONS DROPDOWN */}
                    {showLocations && filteredLocations.length > 0 && (
                        <div className='absolute top-full left-0 w-36 sm:w-44 bg-white border rounded shadow-lg z-50 max-h-40 overflow-y-auto'>
                            <ul className='text-xs'>
                                {filteredLocations.map((location, index) => (
                                    <li key={index} className='p-2'>
                                        <div className='flex justify-between items-center'>
                                            <span className='flex flex-col'>
                                                {location.location && LocationNameEnum[location.location.locationName]
                                                    ? LocationNameEnum[location.location.locationName]
                                                    : location.location?.locationName || 'Pole saadaval'}
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
                <a href={linkToProduct} target="_blank" rel="noopener noreferrer" className={`md:block ${isShowingInfo ? '' : 'hidden'}`}>
                    <button className='sm:w-24 sm:text-base text-sm w-16 h-max border border-gray-700 rounded p-1'>E-poodi</button>
                </a>
            
                {/* Store Logo */}
                <div className={`transition duration-150 ease-in w-14 sm:w-24 h-max md:block ${isShowingInfo ? '' : 'hidden'}`}>
                    <img src={`/${logos[store]}`} alt={store} className='object-contain' />
                </div>


                {/* Mobile button */}
                <button className='md:hidden cursor-pointer' onClick={() => setIsShowingInfo(!isShowingInfo)}>
                    <svg width="20px" height="20px" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M8 12C9.10457 12 10 12.8954 10 14C10 15.1046 9.10457 16 8 16C6.89543 16 6 15.1046 6 14C6 12.8954 6.89543 12 8 12Z" fill="#000000"/>
                        <path d="M8 6C9.10457 6 10 6.89543 10 8C10 9.10457 9.10457 10 8 10C6.89543 10 6 9.10457 6 8C6 6.89543 6.89543 6 8 6Z" fill="#000000"/>
                        <path d="M10 2C10 0.89543 9.10457 -4.82823e-08 8 0C6.89543 4.82823e-08 6 0.895431 6 2C6 3.10457 6.89543 4 8 4C9.10457 4 10 3.10457 10 2Z" fill="#000000"/>
                    </svg>
                </button>
            </div>
        </div>
    );
}

export default SearchResult;
