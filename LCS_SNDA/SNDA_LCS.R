setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Purchase_Insider_70777.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Purchase_Insider_70777', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

#====================================================================================================================

setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Purchase_Insider_100483.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Purchase_Insider_100483', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

#====================================================================================================================

setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Purchase_Insider_100484.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Purchase_Insider_100484', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

#====================================================================================================================

setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Purchase_Insider_126013.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Purchase_Insider_126013', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

#====================================================================================================================

setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Sale_Insider_73820.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Sale_Insider_73820', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

#====================================================================================================================

setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Sale_Insider_82136.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Sale_Insider_82136', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

#====================================================================================================================

setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Sale_Insider_82140.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Sale_Insider_82140', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

#====================================================================================================================

setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Sale_Insider_82255.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Sale_Insider_82255', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

#====================================================================================================================

setwd("C:/Users/samee/Documents/CS584/code_javaSE/cs584/LCS_SNDA")
dist = read.csv("lcs_snda_Sale_Insider_90605.csv", h=F)
str(dist)
dist$V1 = as.Date(dist$V1, "%m/%d/%Y")
plot(x=dist$V1, y=dist$V2, main='Sale_Insider_90605', xlab='Year', ylab='Signed normalized dollar amount',pch=19)
abline(h=0)
grid(col='black')

