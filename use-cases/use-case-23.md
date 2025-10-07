# USE CASE: 23 The population of people, people living in cities, and people not living in cities in each continent.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the total population of people, people living in cities, and people not living in cities in each continent.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city, country, and continent population data.

### Success End Condition

System generates report and displays a list for each continent: total population, people living in cities and population not living in cities.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation.

### Trigger

"Population Reports --> Continent Population" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Population Reports --> Continent Population.
2. The System executes the query.
3. Database returns sorted data set.
4. User views report.

## EXTENSIONS

2. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Filter or sorting option for individual continent name.

## SCHEDULE

**DUE DATE**: Release 2.0
