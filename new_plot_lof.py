"""
=================================================
Anomaly detection with Local Outlier Factor (LOF)
=================================================

This example presents the Local Outlier Factor (LOF) estimator. The LOF
algorithm is an unsupervised outlier detection method which computes the local
density deviation of a given data point with respect to its neighbors.
It considers as outlier samples that have a substantially lower density than
their neighbors.

The number of neighbors considered, (parameter n_neighbors) is typically
chosen 1) greater than the minimum number of objects a cluster has to contain,
so that other objects can be local outliers relative to this cluster, and 2)
smaller than the maximum number of close by objects that can potentially be
local outliers.
In practice, such informations are generally not available, and taking
n_neighbors=20 appears to work well in general.
"""
print(__doc__)
import sys
import numpy as np
import matplotlib.pyplot as plt
from sklearn.neighbors import LocalOutlierFactor

np.random.seed(42)
import csv

# Generate train data
#X = 0.3 * np.random.randn(9264, 2)
# Generate some abnormal novel observations
#X_outliers = np.random.uniform(low=-4, high=4, size=(5, 2))
#X = np.r_[X + 2, X - 2, X_outliers]
X = np.empty((0,2), int)
with open(sys.argv[1]) as csvfile:
#with open('purchase_sim_outlierscores_Big.csv') as csvfile:
    reader = csv.DictReader(csvfile)
    for row in reader:
        print(row['NodeCount'], row['EdgeCount'])
        #X = np.append(X, np.array([row['NodeCount'], row['EdgeCount']]), axis=0)
        X = np.vstack((X, np.array([np.log10( int(row['NodeCount']) ), np.log10(int(row['EdgeCount']))])))

# fit the model
clf = LocalOutlierFactor(n_neighbors=5, metric='euclidean', algorithm='brute')#try other metrics ref:http://scikit-learn.org/stable/modules/generated/sklearn.neighbors.LocalOutlierFactor.html
y_pred = clf.fit_predict(X)

thefile = open(sys.argv[2], 'w')
for item in clf.negative_outlier_factor_:
    thefile.write("%s\n" % item)
thefile.close()
