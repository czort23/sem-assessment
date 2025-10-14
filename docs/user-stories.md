**User Stories for Each Report Type**

**1\. Country Reports**

- **As a** Data Analyst, **I want** to view all countries processed by world, continent, or region ordered by population, from largest to smallest,
 **so that** I can understand population distribution at different geographic levels.
- **As a** Data Analyst, **I want** to view the top N most populated countries processed by world, continent or region where N is provided by the user,
 **so that** I can understand population distribution at different geographic levels.

**2\. City Reports**

- **As a** Data Analyst, **I want** to view all the cities processed by world, continent, region, country or district ordered by population from largest to smallest, 
**so that** I can understand population distribution at different geographic levels.
- **As a** Data Analyst, **I want** to view the top N most populated cities processed by world, continent, region, 
country or district where N is provided by the user **so that** I can understand worldwide population distribution.

**3\. Capital City Reports**

- **As a** Data Analyst, **I want** to view all the capital cities processed by world, continent or region ordered by population from largest to smallest, 
**so that** I can understand population distribution at different geographic levels.
- **As a** Data Analyst, **I want** to view the top N most populated capital cities processed by world, continent or region where N is provided by the user
 **so that** I can understand population distribution at different geographic levels.

**4\. Population Reports**

- **As a** Data Analyst, **I want** to view population of people living in cities vs. people not living in cities processed by continent, 
region or country ordered by population from largest to smallest, **so that** I can understand urbanization trends.
- **As a** Data Analyst, **I want** to view total population processed by world, continent, region, country, district or city 
**so that** I can understand the population distribution.

**5\. Language Reports**

- **As a** Data Analyst, **I want** to view total number of people who speak Chinese, English, Hindu, Spanish or Arabic, 
**so that** I can analyse language demographics.
- **As a** Data Analyst, **I want** to see what percentage of the world population speaks each of these languages ordered from largest to smaller, 
**so that** I can analyse language demographics.

**Conditions of Satisfaction**

- The system requires the user to enter a positive integer value for N.
- In case N is larger that total number of countries in the database, the system returns all the countries.
- Response time should be acceptable (reports should be produced within 5 secs max).
- The country report includes the columns: Code, Name, Continent, Region, Population and Capital.
- The city report includes the columns: Name, Country, District and Population.
- The capital city report includes the columns: Name, Country and Population.
- The population report includes the columns: Name of continent/region/country, total population, total population living in cities (including a %), 
total population not living in cities (including a %).
- If database unavailable or query fails, display appropriate error message.