import React from 'react';

function Header() {
    return (
        <div className='w-full h-16 drop-shadow bg-white flex justify-center'>
            <div className='w-full max-w-7xl flex'>
                {/* Left Section - eMATERJAL */}
                <div className='flex-none w-1/6 h-16 flex justify-center items-center bg-white'>
                    <h1 className='font-sans font-bold cursor-default'>eMATERJAL</h1>
                </div>
                {/* Right Section - REKLAAM */}
                <div className='flex-grow h-16 flex justify-center items-center bg-blue-800'>
                    <h1 className='font-sans font-bold text-white cursor-default'>REKLAAM</h1>
                </div>
            </div>
        </div>
    );
}

export default Header;
