#!/bin/bash

cmd=$(`cat test.json | jq -r ".test1.name"`)
echo -e $cmd > user_output
res=$(`cat test.json | jq -r ".test1.output"`)
echo -e $res > good_output
c_user=`cat user_output`
c_good=`cat good_output`
dif=$(diff -u user_output good_output)

if [ $? -eq 0 ]
then
    echo "File are the same"
    rm user_output good_output
else
    echo "got: \"${c_user}\""
    echo "expected: \"${c_good}\""
    rm user_output good_output
fi
