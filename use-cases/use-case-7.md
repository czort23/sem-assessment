# USE CASE: 7 All the cities in the world organised by largest population to smallest.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the cities in the world organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city data liked to country records.

### Success End Condition

System generates report and displays all cities ordered by population from largest to smallest.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"City Reports --> All Cities in the World" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> All Cities in the World.
2. The System executes the query.
3. Database returns sorted data set.
4. User views report.

## EXTENSIONS

2. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
