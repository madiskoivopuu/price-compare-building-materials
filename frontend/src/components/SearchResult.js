import React, { useState } from 'react'

function SearchResult({name, linkToPicture, linkToProduct, price, unit, store}) {
  const logos = {
    "BAUHOF": "bauhof.png",
    "KRAUTA": "krauta.jpg",
      "ESPAK": "espak.jpg",
  }

  const [isExpanded, setIsExpanded] = useState(false)

  return (
    
    <div className='w-full h-20 rounded flex items-center justify-between  pr-4 bg-white shadow-md'>
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
      <div className='w-max h-full flex items-center gap-4 ml-2 flex-shrink-0'>
        <h1><strong>{price}€</strong><small>&nbsp;/&nbsp;{unit}</small></h1>  
        <a href={linkToProduct} className={`md:block ${isExpanded ? '' : 'hidden' } `}>
            <button className='w-24 h-max border border-gray-700 rounded p-1'>Saadavus</button>
        </a>
        <a href={linkToProduct} className={`md:block ${isExpanded ? '' : 'hidden' } `}>
            <button className='w-24 h-max border border-gray-700 rounded p-1'>Poodi</button>
        </a>
        <div className={`transition duration-150 ease-in w-24 h-max md:block ${isExpanded ? '' : 'hidden' } `}>
          <img src={`/${logos[store]}`} alt={store} className='object-contain' />
        </div>
        {/* MOBILE BUTTON */}
        <button className='md:hidden' onClick={() => setIsExpanded(!isExpanded)}>
          <svg fill="#000000" width="20px" height="20px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12 10c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm0-6c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm0 12c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z"/></svg>
        </button>
      </div>
    </div>
  )
}

export default SearchResult