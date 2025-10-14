/*
===============================================================================
DESCRIPTION:
    This file contains all SQL queries used by the Population Reporting System.

INSTRUCTIONS:
-------------------------------------------------------------------------------
1. Each query must have a comment and a unique key name.
2. Follow this exact pattern:

        -- [Short description of the query]
        -- name: [query_key]
        <SQL statement>;

    - Example:

        -- Get all countries in the world ordered by population (descending)
        -- name: all_countries
        SELECT Code, Name, Continent, Region, Population, Capital
        FROM country
        ORDER BY Population DESC;

3. Use '?' placeholders for user parameters.
4. To retrieve a query in code:
        String sql = QueryLoader.get("all_countries");
===============================================================================
*/
