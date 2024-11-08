import React, { useState } from 'react';
import LocationFilter from './LocationFilter';

function Filters({ selectedLocations, setSelectedLocations }) {
    const [isMinimized, setIsMinimized] = useState(false);

    return (
        <div className='w-full mb-4 p-4 bg-white rounded shadow-md'>
            <div className='flex justify-between items-center'>
                <h2 className='text-lg font-semibold'>Filtrid</h2>
                <button onClick={() => setIsMinimized(!isMinimized)} className='text-blue-500'>
                    {isMinimized ? 'Näita' : 'Peida'}
                </button>
            </div>
            {!isMinimized && (
                <div className='mt-2'>
                    {/* Location Filter */}
                    <LocationFilter selectedLocations={selectedLocations} setSelectedLocations={setSelectedLocations} />

                    {/* Add more filters here in the future */}
                </div>
            )}
        </div>
    );
}

export default Filters;
