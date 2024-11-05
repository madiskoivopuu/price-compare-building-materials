import React from 'react'
import Categories from './Categories'
import Hero from './Hero'

function Main({ isMobileMenuExtended }) {
  return (
    <div className={`w-full h-max flex  bg-gray-100 ${isMobileMenuExtended ? '' : ''}`}>
        <Categories isMobileMenuExtended={isMobileMenuExtended} />
        <Hero />
    </div>
  )
}

export default Main