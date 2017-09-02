/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.dataprovider.api;

import de.esports.aeq.ts3.bot.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link JpaRepository} to manage {@link Application} objects.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 27.08.2017
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    /**
     * Returns a {@link List} of {@link Application} objects that match the given applicant`s name.
     *
     * @param applicant the applicant`s name of the application
     * @return a {@link List} of {@link Application} objects that match the given applicant`s name
     */
    List<Application> findByApplicant(String applicant);

    /**
     * Returns a {@link List} of {@link Application} objects that match the given status.
     *
     * @param status the status of the application
     * @return a {@link List} of {@link Application} objects that match the given status
     */
    List<Application> findByStatus(String status);
}