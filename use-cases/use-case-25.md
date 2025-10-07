# USE CASE: 25 The population of people, people living in cities, and people not living in cities in each country.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the total population in each country, living in cities and not living in cities.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city and country population data.

### Success End Condition

System generates report and displays a list for each country: total population, people living in cities and population of people not living in cities.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation.

### Trigger

"Population Reports --> Country Population" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Population Reports --> Country Population.
2. The System executes the query.
3. Database returns sorted data set.
4. User views report.

## EXTENSIONS

2. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Filter or sorting option for regions, continent and country.

## SCHEDULE

**DUE DATE**: Release 2.0
