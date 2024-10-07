import React from 'react'
import { useState, useEffect } from 'react'

function Hero() {
    const [items, setItems] = useState({})
    const [q, setQ] = useState('')
    const [inputValue, setInputValue] = useState('')

    const fetchFruit = useEffect(() => {
        fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${q}`)
        .then((res) => res.json())
        .then((res) => setItems(res))
    }, [q])
    

    return (
    <div className='w-full md:w-3/4 h-max p-10'>
        <form onSubmit={(e) => {
            e.preventDefault()
            setQ(inputValue)
        }}
            className='flex border-2 border-gray-200 rounded'
        >
            <input 
            className='w-full h-12 pl-2' 
            type='text' 
            placeholder='Otsi toodet'
            onChange={(e) => setInputValue(e.target.value)}
            > 
            </input>
            <button type='submit' className='mx-2'>
                <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M15.7955 15.8111L21 21M18 10.5C18 14.6421 14.6421 18 10.5 18C6.35786 18 3 14.6421 3 10.5C3 6.35786 6.35786 3 10.5 3C14.6421 3 18 6.35786 18 10.5Z" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
            </button>
        </form>
        <h1>{items.length > 0 ? items[0].phonetic : "no response"}</h1>

        
    </div>
    )
}

export default Hero