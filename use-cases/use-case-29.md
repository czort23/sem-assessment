# USE CASE: 29 The population of a country.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the total population of the selected country.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid population data for all countries. 
User enters/selects a valid country name.

### Success End Condition

System generates report and displays the total population of the selected country.

### Failed End Condition

If query fails or data unavailable error is displayed.
User enters/selects a valid country.

### Primary Actor

Organisation.

### Trigger

"Population Reports --> Country Population" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Population Reports --> Country Population.
2. The user enters/selects country name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. Invalid country name: "Please enter a valid country name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

Dropdown list of all countries.

## SCHEDULE

**DUE DATE**: Release 2.0
