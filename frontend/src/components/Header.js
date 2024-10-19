import React from 'react'

function Header() {
  return (
    <>
    <div className='w-full h-32 md:h-16 drop-shadow bg-white flex justify-center'>
        <div className='w-full h-32 md:h-16 max-w-7xl flex flex-wrap justify-between items-center'>
            <div className='w-full md:w-1/4 h-16 flex justify-center items-center'>
                <h1 className='font-sans font-bold hover:animate-spin cursor-default'>eMaterjal</h1>
            </div>
            <img alt='dog' className='w-full md:w-3/4 h-16' src='https://cdn-fpbjb.nitrocdn.com/BgnUcCadFhrlhRbGUXsTJeHYOawZbpXn/assets/images/optimized/rev-9d94d71/hearingsense.com.au/wp-content/uploads/2022/01/8-Fun-Facts-About-Your-Dog-s-Ears.webp' />
        </div>
    </div>
    </>
  )
}

export default Header