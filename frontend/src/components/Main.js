import React from 'react'
import Categories from './Categories'
import Hero from './Hero'

function Main() {
  return (
    <div className='w-full h-full flex bg-gray-100'>
        <Categories/>
        <Hero/>
    </div>
  )
}

export default Main