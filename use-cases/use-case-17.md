# USE CASE: 17 All the capital cities in the world organised by largest population to smallest.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the cities in the world organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city, country records with correct links between country and capital city.

### Success End Condition

System generates report and displays a list of all capital cities in the world ordered from largest to the smallest.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"City Reports --> All Capital Cities" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> All Capital Cities.
2. The System executes the query.
3. Database returns sorted data set.
4. User views report.

## EXTENSIONS

2. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Selection option to pick between all capital cities in the world, continent or region.

## SCHEDULE

**DUE DATE**: Release 1.0
