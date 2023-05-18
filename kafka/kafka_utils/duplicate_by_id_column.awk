BEGIN { FS = "," }
{
    id=$1
    # Keep count of the fields in second column
    count[id]++;

    # Save the line the first time we encounter a unique field
    if (count[id] == 1)
        first[id] = $0;

    # If we encounter the field for the second time, print the
    # previously saved line
    if (count[id] == 2)
        print first[id];

    # From the second time onward. always print because the field is
    # duplicated
    if (count[id] > 1)
        print
}