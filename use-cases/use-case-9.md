# USE CASE: 9 All the cities in a region organised by largest population to smallest.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the cities within a selected region organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city and country data with region fields.

### Success End Condition

System generates report and displays all cities within the selected region ordered by largest to the smallest population.

### Failed End Condition

If query fails or data unavailable error is displayed.
Invalid region name is entered.

### Primary Actor

Organisation user.

### Trigger

"City Reports --> Cities by Region" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> Cities by Region.
2. The user types/picks region name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. The user enters an invalid region name: "Please enter a valid region name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Dropdown menu to select a region.

## SCHEDULE

**DUE DATE**: Release 1.0
