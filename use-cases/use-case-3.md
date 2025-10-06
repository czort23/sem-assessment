# USE CASE: 3 All the countries in a region organised by largest population to smallest. 

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the countries in a region organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active and includes region data linked to countries.

### Success End Condition

System generates report and displays all countries in the chosen region ordered by population from largest to smallest.

### Failed End Condition

If query fails or data unavailable error is displayed.
The user enters an invalid region name.

### Primary Actor

Organisation.

### Trigger

"View Countries by Continent" is selected and specifies a continent.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Country Reports -> Countries by Region
2. The System allows to select or type region name.
3. The user picks the region.
4. The System executes the query.
5. Database returns a list of countries matching the specified region.
6. User views report.

## EXTENSIONS

4. The user enters an invalid or misspelled region name.
   i. System displays "Invalid continent name. Try again."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0
