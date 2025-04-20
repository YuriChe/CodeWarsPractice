package org.practice.kyu5.ranking_system;

// TODO: create the MixUp class/object
// it must support rank, progress, and the incProgress(int rank) method

import java.util.stream.IntStream;

public class RankingSystem {

    static int[] scales = new int[] {-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8};

    public int rank = -8;
    public int progress = 0;

    public void incProgress (int complexity) {

        if (rank == 8) {
            progress = 0;
            return;
        }

        int indexComplexity = IntStream.range(0, scales.length)
                .filter(i -> scales[i] == complexity)
                .findFirst()
                .orElse(-1);

        int indexRank = IntStream.range(0, scales.length)
                .filter(i -> scales[i] == rank)
                .findFirst()
                .orElse(-1);

        if (indexComplexity == -1 || indexRank == -1) {
            throw new IllegalArgumentException();
        }

        int diff = indexComplexity - indexRank;

        if ( diff < -1) {
            return;
        } else if (diff == -1) {
            progress += 1;
        } else if (diff == 0) {
            progress += 3;
        } else if (diff > 0) {
            progress += 10 * diff * diff;
        }

        int overcharge = progress / 100;

        if (overcharge >= 1) {
            indexRank = Math.min(15, indexRank + overcharge);
            progress = progress - overcharge * 100;
        }

        rank = scales[indexRank];

        if (rank == 8) {
            progress = 0;
        }
    }
}
