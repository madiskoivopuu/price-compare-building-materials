import React from 'react'

function SearchResult({name, linkToPicture, linkToProduct, price, unit, store}) {
  const logos = {
    "BAUHOF": "bauhof.png",
    "KRAUTA": "krauta.jpg"
  }

  return (
    <div className='w-full h-20 rounded flex items-center justify-between  pr-4 bg-white shadow-md'>
      
      <div className='flex justify-start items-center h-full'>
        <div className='w-20 h-20 p-1 flex-none'>
          <img alt={name} src={linkToPicture} className='object-contain w-full h-full' />
        </div>
    	  <h1 className='truncate'>{name}</h1>
        <h1><strong>{price}€</strong><small>&nbsp;/&nbsp;{unit}</small></h1>
      </div>
        <div className='w-max h-full flex items-center gap-4 ml-2'>
            <a href={linkToProduct}>
                <button className='w-24 h-max border border-gray-700 rounded p-1'>Saadavus</button>
            </a>
            <a href={linkToProduct}>
                <button className='w-24 h-max border border-gray-700 rounded p-1'>Poodi</button>
            </a>
            <div className='w-24 h-max'>
              <img src={`/${logos[store]}`} alt={store} className='object-contain' />
            </div>
        </div>
    </div>
  )
}

export default SearchResult