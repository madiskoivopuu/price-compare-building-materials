import React from 'react';

function SearchHeader({ totalProducts, currentPage, setCurrentPage, productsPerPage }) {
    const totalPages = Math.ceil(totalProducts / productsPerPage);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    const renderPageNumbers = () => {
        const pageNumbers = [];
        const visiblePages = 4;

        if (totalPages <= visiblePages + 1) {
            for (let i = 1; i <= totalPages; i++) {
                pageNumbers.push(
                    <button
                        key={i}
                        onClick={() => handlePageChange(i)}
                        className={`w-8 h-8 p-2 border rounded-full flex items-center justify-center ${i === currentPage ? 'bg-gray-300' : ''}`}
                    >
                        {i}
                    </button>
                );
            }
        } else {
            pageNumbers.push(
                <button
                    key={1}
                    onClick={() => handlePageChange(1)}
                    className={`w-8 h-8 p-2 border rounded-full flex items-center justify-center ${1 === currentPage ? 'bg-gray-300' : ''}`}
                >
                    1
                </button>
            );

            let startPage;
            let endPage;

            if (currentPage >= totalPages - visiblePages) {
                startPage = totalPages - visiblePages;
                endPage = totalPages;
            } else {
                startPage = Math.max(2, currentPage);
                endPage = Math.min(startPage + visiblePages - 1, totalPages - 1);
            }

            if (startPage > 2) {
                pageNumbers.push(<span key="dots1">...</span>);
            }

            for (let i = startPage; i <= endPage; i++) {
                pageNumbers.push(
                    <button
                        key={i}
                        onClick={() => handlePageChange(i)}
                        className={`w-8 h-8 p-2 border rounded-full flex items-center justify-center ${i === currentPage ? 'bg-gray-300' : ''}`}
                    >
                        {i}
                    </button>
                );
            }

            if (endPage < totalPages - 1) {
                pageNumbers.push(<span key="dots2">...</span>);
            }

            if (endPage < totalPages) {
                pageNumbers.push(
                    <button
                        key={totalPages}
                        onClick={() => handlePageChange(totalPages)}
                        className={`w-8 h-8 p-2 border rounded-full flex items-center justify-center ${totalPages === currentPage ? 'bg-gray-300' : ''}`}
                    >
                        {totalPages}
                    </button>
                );
            }
        }

        return pageNumbers;
    };

    return (
        <div className="flex justify-between items-center p-4 border-b">
            <div className="text-gray-700">
                <p>Leitud {totalProducts} toodet</p>
            </div>
            <div className="flex items-center gap-2 justify-center flex-grow">
                <button
                    onClick={() => handlePageChange(currentPage - 1)}
                    disabled={currentPage === 1}
                    className={`p-2 transition duration-200 ${currentPage === 1 ? 'opacity-50' : ''}`}
                >
                    <span
                        className={`${currentPage !== 1 ? 'hover:text-blue-500' : 'text-gray-500'}`}
                    >
                        {'<'}
                    </span>
                </button>
                {renderPageNumbers()}
                <button
                    onClick={() => handlePageChange(currentPage + 1)}
                    disabled={currentPage === totalPages}
                    className={`p-2 transition duration-200 ${currentPage === totalPages ? 'opacity-50' : ''}`}
                >
                    <span
                        className={`${currentPage !== totalPages ? 'hover:text-blue-500' : 'text-gray-500'}`}
                    >
                        {'>'}
                    </span>
                </button>
            </div>
        </div>
    );
}

export default SearchHeader;
