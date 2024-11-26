import React from 'react';

function SearchHeader({ totalProducts, currentPage, setCurrentPage, productsPerPage, isLoading }) {
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
                        className={`w-8 h-8 p-2 border rounded-full flex items-center justify-center border-1 border-gray-700 ${i === currentPage ? 'border' : ''}`}
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
                    className={`w-8 h-8 p-2 rounded-full flex items-center justify-center border-1 border-gray-700 ${1 === currentPage ? 'border' : ''}`}
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
                        className={`w-8 h-8 p-2 rounded-full flex items-center justify-center border-1 border-gray-700 ${i === currentPage ? 'border' : ''}`}
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
                        className={`w-8 h-8 p-2 rounded-full flex items-center justify-center ${totalPages === currentPage ? 'border' : ''}`}
                    >
                        {totalPages}
                    </button>
                );
            }
        }

        return pageNumbers;
    };

    return (
        <div className="flex justify-between items-center py-4 border-t border-gray-700">
            <div className="text-gray-700">
                <p>Leitud {isLoading ? <span className='animate-pulse'>...</span> : totalProducts} toodet</p>
            </div>
            <div className="flex items-center gap-2 justify-center">
                <button
                    onClick={() => handlePageChange(currentPage - 1)}
                    disabled={currentPage === 1 || isLoading}
                    className={`p-2 transition duration-200 ${currentPage === 1 || isLoading ? 'opacity-50' : ''}`}
                >
                    <span
                        className={`${!isLoading && currentPage !== 1 ? 'hover:text-blue-500' : 'text-gray-500'}`}
                    >
                        {'<'}
                    </span>
                </button>
                {!isLoading && (
                    <div className='flex gap-2'>{renderPageNumbers()}</div>
                )}
                <button
                    onClick={() => handlePageChange(currentPage + 1)}
                    disabled={currentPage === totalPages || isLoading}
                    className={`p-2 transition duration-200 ${currentPage === totalPages || isLoading ? 'opacity-50' : ''}`}
                >
                    <span
                        className={`${!isLoading && currentPage !== totalPages ? 'hover:text-blue-500' : 'text-gray-500'}`}
                    >
                        {'>'}
                    </span>
                </button>
            </div>
            <div className='w-24'></div>
        </div>
    );
}

export default SearchHeader;
