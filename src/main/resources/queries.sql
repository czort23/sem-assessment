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
/*
===============================================================================
POPULATION REPORTS
===============================================================================
*/
/*
--Show each continent, total population in each continent,  population living in cities,
and  population not living in cities  in each continent, ordered by total population (largest to smallest)
name: continent_population_summary
*/


SELECT
    co.Continent,
    SUM(co.Population) AS total_population,
    SUM(ci.city_population) AS city_population,
    (SUM(co.Population) - SUM(ci.city_population)) AS non_city_population
FROM country AS co
         LEFT JOIN (
    SELECT
        CountryCode,
        SUM(Population) AS city_population
    FROM city
    GROUP BY CountryCode
) AS ci
                   ON co.Code = ci.CountryCode
GROUP BY co.Continent
ORDER BY total_population DESC;


-- Show each Region, total population in eaach region, people living in cities, and people not living in cities
-- for each region, ordered by total population (largest to smallest)
-- name: region_population_summary
SELECT
    co.Region,
    SUM(co.Population) AS total_population,
    SUM(ci.city_population) AS city_population,
    (SUM(co.Population) - SUM(ci.city_population)) AS non_city_population
FROM country AS co
         LEFT JOIN (
    SELECT CountryCode, SUM(Population) AS city_population
    FROM city
    GROUP BY CountryCode
) AS ci ON co.Code = ci.CountryCode
GROUP BY co.Region
ORDER BY total_population DESC;

-- Show each Country, total population, people living in cities, and people not living in cities
-- for each country, ordered by total population (largest to smallest)
-- name: country_population_summary
SELECT
    co.Name AS Country,
    SUM(co.Population) AS total_population,
    SUM(ci.city_population) AS city_population,
    (SUM(co.Population) - SUM(ci.city_population)) AS non_city_population
FROM country AS co
         LEFT JOIN (
    SELECT CountryCode, SUM(Population) AS city_population
    FROM city
    GROUP BY CountryCode
) AS ci ON co.Code = ci.CountryCode
GROUP BY co.Name
ORDER BY total_population DESC;

-- Show population for a specific continent provided by the user
-- name: population_by_continent
SELECT
    co.Continent,
    SUM(co.Population) AS total_population,
    SUM(ci.city_population) AS city_population,
    (SUM(co.Population) - SUM(ci.city_population)) AS non_city_population
FROM country AS co
         LEFT JOIN (
    SELECT CountryCode, SUM(Population) AS city_population
    FROM city
    GROUP BY CountryCode
) AS ci ON co.Code = ci.CountryCode
WHERE co.Continent = ?
GROUP BY co.Continent;

-- Show population for a specific region provided by the user
-- name: population_by_region
SELECT
    co.Region,
    SUM(co.Population) AS total_population,
    SUM(ci.city_population) AS city_population,
    (SUM(co.Population) - SUM(ci.city_population)) AS non_city_population
FROM country AS co
         LEFT JOIN (
    SELECT CountryCode, SUM(Population) AS city_population
    FROM city
    GROUP BY CountryCode
) AS ci ON co.Code = ci.CountryCode
WHERE co.Region = ?
GROUP BY co.Region;

-- Show population for a specific country provided by the user
-- name: population_by_country
SELECT
    co.Name AS Country,
    co.Population AS total_population,
    ci.city_population,
    (co.Population - ci.city_population) AS non_city_population
FROM country AS co
         LEFT JOIN (
    SELECT CountryCode, SUM(Population) AS city_population
    FROM city
    GROUP BY CountryCode
) AS ci ON co.Code = ci.CountryCode
WHERE co.Name = ?;