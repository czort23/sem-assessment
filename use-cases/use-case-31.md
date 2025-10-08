# USE CASE: 31 The population of a city.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the total population of the selected city.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city with population data.
User enters/selects a valid city name.

### Success End Condition

System generates report and displays the total population of the selected city.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"Population Reports --> City Population" is selected.
User enters/selects a valid city name.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Population Reports --> City Population.
2. The user enters/selects city name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. Invalid city name: "Please enter a valid city name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

Dropdown list of all the cities for selection.
Allow filtering by country or district.

## SCHEDULE

**DUE DATE**: Release 1.0
