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
        -- name: all_countries_desc
        SELECT Code, Name, Continent, Region, Population, Capital
        FROM country
        ORDER BY Population DESC;

3. Use '?' placeholders for user parameters.
4. To retrieve a query in code:
        String sql = QueryLoader.get("all_countries_desc");
===============================================================================
 */

/*
===============================================================================
 COUNTRY REPORTS
===============================================================================
 */

-- All the countries in the world organised by largest population to smallest.
-- name: all_countries
SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, s.Name AS Capital
FROM country c
JOIN city s ON c.Capital = s.ID
ORDER BY c.Population DESC;

-- All the countries in a continent organised by largest population to smallest.
-- name: all_countries_by_continent
SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, s.Name AS Capital
FROM country c
JOIN city s ON c.Capital = s.ID
WHERE c.Continent = ?
ORDER BY c.Population DESC;

-- All the countries in a region organised by largest population to smallest.
-- name: all_countries_by_region
SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, s.Name AS Capital
FROM country c
JOIN city s ON c.Capital = s.ID
WHERE c.Region = ?
ORDER BY c.Population DESC;

-- The top N populated countries in the world where N is provided by the user.
-- name: top_n_countries
SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, s.Name AS Capital
FROM country c
JOIN city s ON c.Capital = s.ID
ORDER BY c.Population DESC
LIMIT ?;

-- The top N populated countries in a continent where N is provided by the user.
-- name: top_n_countries_by_continent
SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, s.Name AS Capital
FROM country c
JOIN city s ON c.Capital = s.ID
WHERE c.Continent = ?
ORDER BY c.Population DESC
LIMIT ?;

-- The top N populated countries in a region where N is provided by the user.
-- name: top_n_countries_by_region
SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, s.Name AS Capital
FROM country c
JOIN city s ON c.Capital = s.ID
WHERE c.Region = ?
ORDER BY c.Population DESC
LIMIT ?;

/*
===============================================================================
 CITY REPORTS
===============================================================================
 */

-- All the cities in the world organised by largest population to smallest.
-- name: all_cities
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
ORDER BY ci.Population DESC;

-- All the cities in a continent organised by largest population to smallest.
-- name: all_cities_by_continent
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
WHERE co.Continent = ?
ORDER BY ci.Population DESC;

-- All the cities in a region organised by largest population to smallest.
-- name: all_cities_by_region
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
WHERE co.Region = ?
ORDER BY ci.Population DESC;

-- All the cities in a country organised by largest population to smallest.
-- name: all_cities_by_country
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
WHERE co.Name = ?
ORDER BY ci.Population DESC;

-- All the cities in a district organised by largest population to smallest.
-- name: all_cities_by_district
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
WHERE ci.District = ?
ORDER BY ci.Population DESC;

-- The top N populated cities in the world where N is provided by the user.
-- name: top_n_cities
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
ORDER BY ci.Population DESC
LIMIT ?;

-- The top N populated cities in a continent where N is provided by the user.
-- name: top_n_cities_by_continent
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
WHERE co.Continent = ?
ORDER BY ci.Population DESC
LIMIT ?;

-- The top N populated cities in a region where N is provided by the user.
-- name: top_n_cities_by_region
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
WHERE co.Region = ?
ORDER BY ci.Population DESC
LIMIT ?;

-- The top N populated cities in a country where N is provided by the user.
-- name: top_n_cities_by_country
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
WHERE co.Name = ?
ORDER BY ci.Population DESC
LIMIT ?;

-- The top N populated cities in a district where N is provided by the user.
-- name: top_n_cities_by_district
SELECT ci.Name AS City, co.Name AS Country, ci.District, ci.Population
FROM city ci
JOIN country co ON ci.CountryCode = co.Code
WHERE ci.District = ?
ORDER BY ci.Population DESC
LIMIT ?;

/*
===============================================================================
 LANGUAGE REPORTS
===============================================================================
 */

-- Number of people who speak Chinese, English, Hindi, Spanish, Arabic (sorted from greatest to smallest, with world %)
-- name: language_breakdown
SELECT
    l.Language,
    ROUND(SUM(c.Population * (l.Percentage / 100)), 0) AS Speakers,
    ROUND(SUM(c.Population * (l.Percentage / 100)) / (SELECT SUM(Population) FROM country) * 100, 2) AS WorldPercentage
FROM countrylanguage l
JOIN country c ON l.CountryCode = c.Code
WHERE l.Language IN ('Chinese','English','Hindi','Spanish','Arabic')
GROUP BY l.Language
ORDER BY Speakers DESC;