import React from 'react'

function SearchResult({name, linkToPicture, linkToProduct, price, unit}) {
  return (
    <div className='w-full h-16 rounded flex items-center justify-between pr-4 bg-gray-100 border'>
        <div className='w-full h-full flex items-center gap-4 truncate'>
            <img alt={name} src={linkToPicture} className='w-auto h-full max-w-24' />
            <h1>{name}</h1>
        </div>
        <div className='w-max h-full flex items-center gap-4 ml-2'>
            <h1 className='font-bold'>{price}€/{unit}</h1>
            <a href={linkToProduct}>
                <button className='w-max h-max bg-gray-200 rounded p-1'>Poodi</button>
            </a>
        </div>
    </div>
  )
}

export default SearchResult