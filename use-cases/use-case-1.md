# USE CASE: 1 All the countries in the world organised by largest population to smallest.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the countries in the world organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active and valid country data is present.

### Success End Condition

Country report displayed in descending order by population.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"Largest to Smallest" is selected.

## MAIN SUCCESS SCENARIO

1. User selects "Largest to Smallest".
2. System shows for filter.
3. User enters selection.
4. System retrieves and sorts data.
5. System displays report.
6. User views report.

## EXTENSIONS

- Database is unavailable -> Error message.
- No results -> "No data found."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
