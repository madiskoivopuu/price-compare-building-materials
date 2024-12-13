import React, { useEffect, useState } from 'react';
import Filter from './Filter';
import LocationFilter from './LocationFilter';

function Filters({ filters, setFilteringData, selectedLocations, setSelectedLocations, clearFiltersTrigger, searchId }) {
    const [isMinimized, setIsMinimized] = useState(false);
    const [temporaryFilteringData, setTemporaryFilteringData] = useState({});

    useEffect(() => {
        // Clear filters when the category changes
        setTemporaryFilteringData({});
        setFilteringData([]);
    }, [clearFiltersTrigger, setFilteringData]);

    useEffect(() => {
        // Dynamically apply filters when temporaryFilteringData changes
        const keywordArray = Object.values(temporaryFilteringData).filter((array) => array.length > 0);
        setFilteringData(keywordArray);
    }, [temporaryFilteringData, setFilteringData]);

    const updateTemporaryFilteringData = (displayName, selectedKeywords) => {
        setTemporaryFilteringData((prev) => {
            const updatedFilteringData = {
                ...prev,
                [displayName]: selectedKeywords,
            };

            // Optionally log the updated state here
            console.log(updatedFilteringData);

            return updatedFilteringData;
        });
    };

    return (
        <div className="w-full mb-10 p-4 bg-white rounded shadow-md">
            <div className="flex justify-between items-center">
                <h2 className="text-lg font-semibold">Filtrid</h2>
                <button onClick={() => setIsMinimized(!isMinimized)} className="text-blue-500">
                    {isMinimized ? 'Näita ↓' : 'Peida ↑'}
                </button>
            </div>
            {!isMinimized && (
                <div className="mt-2 flex flex-wrap gap-4">

                        <LocationFilter selectedLocations={selectedLocations} setSelectedLocations={setSelectedLocations} />

                    {filters.map((filter) => (
                        <Filter
                            key={filter.displayName}
                            displayName={filter.displayName}
                            keywords={filter.keywords}
                            filteringData={temporaryFilteringData[filter.displayName] || []}
                            onFilterChange={(selectedKeywords) =>
                                updateTemporaryFilteringData(filter.displayName, selectedKeywords)
                            }
                        />
                    ))}
                </div>
            )}
        </div>
    );
}

export default Filters;
