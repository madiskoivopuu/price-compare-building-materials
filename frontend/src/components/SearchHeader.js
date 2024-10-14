import React from 'react'

function SearchHeader({ totalProducts, currentPage, setCurrentPage, productsPerPage}) {

  const totalPages = Math.ceil(totalProducts / productsPerPage);
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  }
  return (
      <div className="flex justify-between items-center p-4 border-b">
        <div className="text-gray-700">
          <p>Leitud {totalProducts} toodet</p>
        </div>
        <div className="flex items-center gap-2 justify-center flex-grow">
          <button
              onClick={() => handlePageChange(currentPage - 1)}
              disabled={currentPage === 1}
              className="p-2 border rounded"
          >
            {'<'}
          </button>
          {Array.from({ length: totalPages }, (_, i) => i + 1).map((page) => (
              <button
                  key={page}
                  onClick={() => handlePageChange(page)}
                  className={`w-8 h-8 p-2 border rounded-full flex items-center justify-center ${page === currentPage ? 'bg-gray-300' : ''}`}
              >
                {page}
              </button>
          ))}
          <button
              onClick={() => handlePageChange(currentPage + 1)}
              disabled={currentPage === totalPages}
              className="p-2 border rounded"
          >
            {'>'}
          </button>
        </div>
      </div>
  );
}

export default SearchHeader