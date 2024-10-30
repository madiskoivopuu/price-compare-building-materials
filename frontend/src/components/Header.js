import React from 'react';

function Header() {
    return (
        <div className='w-full md:h-16 h-32 drop-shadow-md bg-white flex justify-center'>
            <div className='w-full flex flex-col md:flex-row'>
                {/* Left Section - eMATERJAL */}
                <div className='md:w-1/4 w-full h-16 flex justify-center items-center bg-white'>
                    <h1 className='font-bold'>eMATERJAL</h1>
                </div>
                {/* Right Section - REKLAAM */}
                <div className='md:w-3/4 w-full h-16 flex justify-center items-center bg-blue-800'>
                    <h1 className='font-bold text-white'>REKLAAM</h1>
                </div>
            </div>
        </div>
    );
}

export default Header;
