# USE CASE: 2 All the countries in a continent organised by largest population to smallest.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the countries in a continent organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active and valid country data and their associated continents is present.

### Success End Condition

System generates report and displays all countries within the chosen continent ordered by population from largest to smallest.

### Failed End Condition

If query fails or data unavailable error is displayed.
The user enters an invalid continent name.

### Primary Actor

Organisation user.

### Trigger

"View Countries by Continent" is selected and specifies a continent.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Country Reports -> Countries by Continent
2. The System allows to select or type continent name.
3. The user picks the continent.
4. The System executes the query.
5. Database returns a list with countries and their continents.
6. User views report.

## EXTENSIONS

3. The user enters an invalid or misspelled continent name.
    i. System displays "Invalid continent name. Try again."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
