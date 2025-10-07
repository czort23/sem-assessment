# USE CASE: 11 All the cities in a district organised by largest population to smallest.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the cities within a selected district organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city and district data.
The user provides a valid district name.

### Success End Condition

System generates report and displays all cities within the selected district, ordered by largest to the smallest population.

### Failed End Condition

If query fails or data unavailable error is displayed.
Invalid district name is entered.

### Primary Actor

Organisation.

### Trigger

"City Reports --> Cities by District" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> Cities by District.
2. The user types/picks district name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. The user enters an invalid district name: "Please enter a valid district name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Dropdown menu to select a district.

## SCHEDULE

**DUE DATE**: Release 2.0
