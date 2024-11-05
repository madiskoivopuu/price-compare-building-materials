import { useState, useEffect } from 'react';
import Header from './components/Header';
import Main from './components/Main';

function App() {

  // Logic for extending mobile menu

  const [isMobileMenuExtended, setIsMobileMenuExtended] = useState(false)
  const extendMobileMenu = () => {
    setIsMobileMenuExtended(!isMobileMenuExtended)
  }

  const resizeSetExpandFalse = () => {
    setIsMobileMenuExtended(false)
  }

  useEffect(() => {
      window.addEventListener('resize', resizeSetExpandFalse)
  }, [])

  return (
    <>
      <Header extendMobileMenu={extendMobileMenu}  />
      <Main isMobileMenuExtended={isMobileMenuExtended} />
    </>
  );
}

export default App;
