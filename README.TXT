
Thomas Phan

This package implements a system to find frequently-occurring idioms.
After experimentation and reading through research papers, I could
not find an approach to find idioms that could be implemented in a
reasonable amount of time and effort. Instead, I implemented a system 
to find top-occurring trigrams, where trigrams can serve as a proxy 
for idioms. The software was written from scratch.


-----------------------------------------------------------------------------
1. COMPILING
-----------------------------------------------------------------------------

I wrote my code in Java. Please use Maven to compile the code (on Mac OS,
Linux, or Windows).

prompt> cd idioms
prompt> mvn install

This will produce an all-in-one jar file:

target/idioms-0.0.1-jar-with-dependencies.jar


-----------------------------------------------------------------------------
2. RUNNING 
-----------------------------------------------------------------------------

The software depends on a pre-tokenized corpus of text. To test my
software, I used a pre-tokenized Wall Street Journal corpus.

Invoke the software as follows:

prompt> java -jar target/idioms-0.0.1-jar-with-dependencies.jar  textfile  topN

where:
textfile is the input pre-tokenized text
topN is the number of most-frequent trigrams to print


From running with the Wall Street Journal text as input, the software produced 
the following results:

    cents a share                 , count=434, prob=0.0004536446
    the company 's                , count=414, prob=0.0004327393
    a year earlier                , count=272, prob=0.0002843118
    New York Stock                , count=240, prob=0.0002508634
    York Stock Exchange           , count=240, prob=0.0002508634
    in the U.S.                   , count=235, prob=0.0002456371
    in New York                   , count=219, prob=0.0002289128
    the New York                  , count=206, prob=0.0002153244
    one of the                    , count=201, prob=0.0002100981
    the end of                    , count=193, prob=0.0002017360
    of the company                , count=173, prob=0.0001808307
    and chief executive           , count=170, prob=0.0001776949
    chief executive officer       , count=170, prob=0.0001776949
    the third quarter             , count=170, prob=0.0001776949
    as well as                    , count=152, prob=0.0001588801
    is expected to                , count=151, prob=0.0001578349
    president and chief           , count=148, prob=0.0001546991
    a year ago                    , count=141, prob=0.0001473822
    said it will                  , count=133, prob=0.0001390201
    the stock market              , count=132, prob=0.0001379749
    the nation 's                 , count=131, prob=0.0001369296
    as much as                    , count=131, prob=0.0001369296
    some of the                   , count=130, prob=0.0001358843
    the company said              , count=126, prob=0.0001317033
    in the past                   , count=114, prob=0.0001191601
    the sale of                   , count=111, prob=0.0001160243
    part of the                   , count=111, prob=0.0001160243
    end of the                    , count=110, prob=0.0001149791
    be able to                    , count=108, prob=0.0001128885
    The company said              , count=105, prob=0.0001097527




