/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    fontFamily: {
      'sans': ['Inter']
    },
    extend: {},
    screens: {
      'md': '986px',
      'sm': '460px'
    }
  },
  plugins: [],
}

