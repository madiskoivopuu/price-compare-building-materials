import React, { useState } from 'react';

function LocationFilter({ selectedLocations, setSelectedLocations }) {
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

    const [isDropdownOpen, setIsDropdownOpen] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');

    const toggleSelection = (location) => {
        if (selectedLocations.includes(location)) {
            setSelectedLocations(selectedLocations.filter(l => l !== location));
        } else {
            setSelectedLocations([...selectedLocations, location]);
        }
    };

    const filteredLocations = Object.entries(LocationNameEnum).filter(([key, value]) =>
        value.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className='mt-2 w-1/3'>
            <label className='block mb-1 font-medium'>Vali asukoht</label>
            <div className='relative'>
                <button
                    onClick={() => setIsDropdownOpen(!isDropdownOpen)}
                    className='w-full border rounded p-2 bg-white flex flex-wrap items-center gap-1'
                >
                    {selectedLocations.length > 0 ? (
                        selectedLocations.map((location, index) => (
                            <span key={index} className='bg-blue-100 text-blue-700 px-2 py-1 rounded flex items-center'>
                                {LocationNameEnum[location]}
                                <button
                                    onClick={(e) => {
                                        e.stopPropagation(); // Prevent dropdown toggle
                                        toggleSelection(location);
                                    }}
                                    className='ml-1 text-red-500'
                                >
                                    x
                                </button>
                            </span>
                        ))
                    ) : (
                        'Vali asukoht'
                    )}
                </button>
                {isDropdownOpen && (
                    <div className='absolute z-10 w-full border rounded bg-white shadow-lg max-h-60 overflow-y-auto'>
                        <input
                            type='text'
                            placeholder='Otsi asukohta...'
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                            className='w-full p-2 border-b'
                        />
                        {filteredLocations.map(([key, value]) => (
                            <div key={key} className='flex items-center p-2 hover:bg-gray-100'>
                                <input
                                    type='checkbox'
                                    value={key}
                                    checked={selectedLocations.includes(key)}
                                    onChange={() => toggleSelection(key)}
                                    className='mr-2'
                                />
                                <label>{value}</label>
                            </div>
                        ))}
                        {filteredLocations.length === 0 && (
                            <div className='p-2 text-gray-500'>Asukohti ei leitud</div>
                        )}
                    </div>
                )}
            </div>
        </div>
    );
}

export default LocationFilter;
