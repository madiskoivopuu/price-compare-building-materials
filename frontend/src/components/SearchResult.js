import React, { useState } from 'react'

function SearchResult({name, linkToPicture, linkToProduct, price, unit, store, locations}) {
  const logos = {
    "BAUHOF": "bauhof.png",
    "KRAUTA": "krauta.jpg",
      "ESPAK": "espak.jpg",
      "DECORA": "decora.png"
  }

    const LocationNameEnum = {
        TALLINN: "Tallinn",
        TARTU: "Tartu",
        TURI: "Türi",
        NARVA: "Narva",
        PARNU: "Pärnu",
        KOHTLA_JARVE: "Kohtla-Järve",
        VILJANDI: "Viljandi",
        VORU: "Võru",
        RAKVERE: "Rakvere",
        SILLAMAE: "Sillamäe",
        MAARDU: "Maardu",
        KURESSAARE: "Kuressaare",
        VALGA: "Valga",
        VASTSELIINA: "Vastseliina",
        HAAPSALU: "Haapsalu",
        KEILA: "Keila",
        POLVA: "Põlva",
        JOGEVA: "Jõgeva",
        MUHU: "Muhu",
        JOHVI: "Jõhvi",
        RAPLA: "Rapla",
        RAPINA: "Räpina",
        PAIDE: "Paide",
        POLTSAMAA: "Põltsamaa"
    };

    const generateMapsLink = (address) => {
        return `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(address)}`
    }

    const [isExpanded, setIsExpanded] = useState(false)
    const [showLocations, setShowLocations] = useState(false)

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
                <h1 className='truncate'>{name}</h1>
            </div>
            {/* BUTTONS AND PRICE */}
            <div className='w-max h-full flex items-center gap-4 ml-2 flex-shrink-0 relative'>
                <h1><strong>{price}€</strong><small>&nbsp;/&nbsp;{unit}</small></h1>

                {/* AVAILABILITY WITH DROPDOWN */}
                <div className="relative">
                    <button
                        onClick={() => {
                            if (locations && locations.length > 0) {
                                setShowLocations(!showLocations);
                            }
                        }}
                        className={`w-64 h-max border rounded p-1 ${(!locations || locations.length === 0) ? 'bg-gray-300 text-gray-600 cursor-not-allowed' : 'border-gray-700'}`}
                        disabled={!locations || locations.length === 0} // Disables the button if no locations
                    >
                        {(!locations || locations.length === 0) ? 'Pole saadavusel' : 'Saadavus'}
                    </button>

                    {/* LOCATIONS DROPDOWN */}
                    {showLocations && locations && locations.length > 0 && (
                        <div className='absolute top-full left-0 w-64 bg-white border rounded shadow-lg z-50'>
                            <ul className='text-sm'>
                                {locations.map((location, index) => (
                                    <li key={index} className='p-2'>
                                        <div className='flex justify-between items-center'>
                            <span>
                                {location.location && LocationNameEnum[location.location.locationName]
                                    ? LocationNameEnum[location.location.locationName]
                                    : location.location?.locationName || 'Unknown Location'},&nbsp;
                                {location.location?.address ? (
                                    <a
                                        href={generateMapsLink(location.location.address)}
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
                                            <span>{location.quantity ? `${location.quantity} tk` : 'teadmata'}</span>
                                        </div>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    )}
                </div>

                {/* "Poodi" Button */}
                <a href={linkToProduct} className={`md:block ${isExpanded ? '' : 'hidden'} `}>
                    <button className='w-24 h-max border border-gray-700 rounded p-1'>E-poodi</button>
                </a>
                <div className={`transition duration-150 ease-in w-24 h-max md:block ${isExpanded ? '' : 'hidden'} `}>
                    <img src={`/${logos[store]}`} alt={store} className='object-contain'/>
                </div>
                {/* MOBILE BUTTON */}
                <button className='md:hidden' onClick={() => setIsExpanded(!isExpanded)}>
                    <svg fill="#000000" width="20px" height="20px" viewBox="0 0 24 24"
                         xmlns="http://www.w3.org/2000/svg">
                        <path
                            d="M12 10c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm0-6c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm0 12c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z"/>
                    </svg>
                </button>
            </div>
        </div>
    )
}

export default SearchResult