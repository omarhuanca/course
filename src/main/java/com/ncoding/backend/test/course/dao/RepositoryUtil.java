package com.ncoding.backend.test.course.dao;

public class RepositoryUtil {

    /**
     * Calculate limit
     *
     * @param maxPageSize   The max page size to get the limit.
     * @param pageSize      The page size to get the limit.
     * @return
     */
    public static int calcLimit(final int maxPageSize, final int pageSize) {
        return Math.min(maxPageSize, Math.max(pageSize, 1));
    }

    /**
     * Calculate offset
     *
     * @param limit         The limit to get the offset.
     * @param pageNumber    The page number to get the offset.
     * @return The calculated offset.
     */
    public static int calcOffset(final int limit, final int pageNumber) {
        return limit * (Math.max(pageNumber, 1) - 1);
    }
}
