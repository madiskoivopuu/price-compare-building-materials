import React from 'react';

function Header({extendMobileMenu}) {

    const handleClick = () => {
        extendMobileMenu()
    }

    return (
        <div className='w-full md:h-16 h-32 drop-shadow-md bg-white flex justify-center'>
            <div className='w-full flex flex-col md:flex-row'>
                {/* Left Section - eMATERJAL */}
                <div className='md:w-1/4 w-full h-16 flex justify-center items-center bg-white'>
                {/* Button that extends the mobile menu */}
                <button 
                    className={`fixed top-3 left-5 w-10 h-10 md:hidden z-[100]`}
                    onClick={handleClick} 
                >
                    <svg width="40px" height="40px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" fill="none" className={`border-1 rounded border-gray-700 hover:bg-gray-200`}>
                        <g stroke="#000000" strokeLinecap="round" strokeWidth="2">
                            <path d="M4 6h16"/>
                            <path d="M4 10h16"/>
                            <path d="M4 14h16"/>
                            <path d="M4 18h16"/>
                        </g>
                    </svg>
                </button>
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
