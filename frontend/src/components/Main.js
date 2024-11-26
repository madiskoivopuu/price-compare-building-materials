import React, { useState } from 'react'
import Categories from './Categories'
import Hero from './Hero'

function Main({ isMobileMenuExtended }) {
  const [selectedCategory, setSelectedCategory] = useState(null)
  const categoryChange = (cat) => { setSelectedCategory(cat) }

  return (
    <div className={`w-full h-max flex  bg-gray-100`}>
        <Categories isMobileMenuExtended={isMobileMenuExtended} categoryChange={categoryChange} selectedCategory={selectedCategory} />
        <Hero selectedCategory={selectedCategory} categoryChange={categoryChange} />
    </div>
  )
}

export default Main